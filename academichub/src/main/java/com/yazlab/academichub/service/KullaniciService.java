package service;

import dto.KullaniciKayitDTO;
import jakarta.xml.soap.*;
import lombok.extern.slf4j.Slf4j;
import model.Kullanicilar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import repository.KullanicilarRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.*;
import java.util.logging.Logger;

@Slf4j
@Service
public class KullaniciService implements UserDetailsService {
    private static final String NVI_URL = "https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx";
    private static final String SOAP_ACTION = "http://tckimlik.nvi.gov.tr/WS/TCKimlikNoDogrula";
    private static final Logger logger = Logger.getLogger(KullaniciService.class.getName());

    private PasswordEncoder passwordEncoder;

    @Autowired
    private KullanicilarRepository kullanicilarRepository;

    private boolean validateTcKimlik(String tc, String ad, String soyad, int dogumYili) {
        SOAPConnection soapConnection = null;
        try {
            // Türkçe karakterleri düzgün şekilde dönüştür
            Locale trLocale = new Locale("tr", "TR");
            String upperAd = ad.toUpperCase(trLocale).trim();
            String upperSoyad = soyad.toUpperCase(trLocale).trim();

            logger.info(String.format("TC Kimlik Doğrulama Parametreleri: TC=%s, Ad=%s, Soyad=%s, DogumYili=%d",
                    tc, upperAd, upperSoyad, dogumYili));

            // SOAP bağlantısı oluştur
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();

            // SOAP mesajı oluştur
            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage soapMessage = messageFactory.createMessage();

            // SOAP headers
            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.addHeader("SOAPAction", SOAP_ACTION);

            // SOAP envelope
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.setPrefix("soap");
            envelope.removeNamespaceDeclaration("SOAP-ENV");
            envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
            envelope.addNamespaceDeclaration("ws", "http://tckimlik.nvi.gov.tr/WS");

            // SOAP body
            SOAPBody soapBody = envelope.getBody();
            soapBody.setPrefix("soap");

            // SOAP body içeriği
            SOAPElement operation = soapBody.addChildElement("TCKimlikNoDogrula", "ws");
            operation.addChildElement("TCKimlikNo", "ws").addTextNode(tc);
            operation.addChildElement("Ad", "ws").addTextNode(upperAd);
            operation.addChildElement("Soyad", "ws").addTextNode(upperSoyad);
            operation.addChildElement("DogumYili", "ws").addTextNode(String.valueOf(dogumYili));

            soapMessage.saveChanges();

            // İstek ve yanıtı logla
            logger.info("SOAP İsteği gönderiliyor...");
            SOAPMessage response = soapConnection.call(soapMessage, NVI_URL);

            // Yanıtı işle
            SOAPBody responseBody = response.getSOAPBody();
            NodeList returns = responseBody.getElementsByTagName("TCKimlikNoDogrulaResult");
            boolean result = returns.getLength() > 0 && Boolean.parseBoolean(returns.item(0).getTextContent());

            logger.info("TC Kimlik doğrulama sonucu: " + result);
            return result;

        } catch (Exception e) {
            logger.severe("TC Kimlik doğrulama hatası: " + e.getMessage());
            return false;
        } finally {
            if (soapConnection != null) {
                try {
                    soapConnection.close();
                } catch (SOAPException e) {
                    logger.warning("SOAP bağlantısı kapatılamadı: " + e.getMessage());
                }
            }
        }
    }

    public Map<String, String> register(String ad, String soyad, String email, String sifre, String rol, String tc, int dogumYili) {
        // Email ve TC kontrolü
        if (kullanicilarRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Bu email adresi zaten kullanımda.");
        }
        if (kullanicilarRepository.findByTc(tc).isPresent()) {
            throw new IllegalArgumentException("Bu TC Kimlik numarası zaten kullanımda.");
        }

        // TC Kimlik doğrulama
        if (!validateTcKimlik(tc, ad, soyad, dogumYili)) {
            throw new IllegalArgumentException("Kimlik bilgileri doğrulanamadı. Lütfen TC Kimlik No, Ad, Soyad ve Doğum Yılı bilgilerinizi kontrol ediniz.");
        }

        // Yeni kullanıcı oluştur
        Kullanicilar kullanici = new Kullanicilar();
        kullanici.setAd(ad);
        kullanici.setSoyad(soyad);
        kullanici.setEmail(email);
        kullanici.setSifre(sifre);
        kullanici.setRol(rol);
        kullanici.setTc(tc);
        kullanici.setDogumYili(dogumYili);


        kullanicilarRepository.save(kullanici);

        return Map.of("message", "Kayıt başarıyla gerçekleştirildi.");
    }

    public List<Kullanicilar> getAllUsers() {
        return kullanicilarRepository.findAll();
    }

    public List<Kullanicilar> getUsersByRole(String rol) {
        return kullanicilarRepository.findByRol(rol);
    }

    public List<Kullanicilar> searchUsersByFullName(String ad, String soyad) {
        if (ad != null && soyad != null) {
            return kullanicilarRepository.findByAdAndSoyad(ad, soyad);
        } else if (ad != null) {
            return kullanicilarRepository.findByAd(ad);
        } else if (soyad != null) {
            return kullanicilarRepository.findBySoyad(soyad);
        }
        return getAllUsers();
    }

    public Kullanicilar getUserByTc(String tc){
        return kullanicilarRepository.findByTc(tc)
                .orElseThrow(()-> new IllegalArgumentException("Tc numarası ile kullanıcı bulunamadı: "+tc));
    }

    public Kullanicilar updateUser(int id, KullaniciKayitDTO updateDTO) {
        Kullanicilar kullanici = kullanicilarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı: " + id));

        // Check if email is being changed and is already in use
        if (!kullanici.getEmail().equals(updateDTO.getEmail()) &&
                kullanicilarRepository.findByEmail(updateDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Bu email adresi zaten kullanımda.");
        }

        // Check if TC is being changed and is already in use
        if (!kullanici.getTc().equals(updateDTO.getTc()) &&
                kullanicilarRepository.findByTc(updateDTO.getTc()).isPresent()) {
            throw new IllegalArgumentException("Bu TC Kimlik numarası zaten kullanımda.");
        }

        // Validate TC Kimlik if critical information is changed
        if (!kullanici.getTc().equals(updateDTO.getTc()) ||
                !kullanici.getAd().equals(updateDTO.getAd()) ||
                !kullanici.getSoyad().equals(updateDTO.getSoyad()) ||
                kullanici.getDogumYili() != updateDTO.getDogumYili()) {

            if (!validateTcKimlik(updateDTO.getTc(), updateDTO.getAd(),
                    updateDTO.getSoyad(), updateDTO.getDogumYili())) {
                throw new IllegalArgumentException("Kimlik bilgileri doğrulanamadı.");
            }
        }

        // Update user information
        kullanici.setAd(updateDTO.getAd());
        kullanici.setSoyad(updateDTO.getSoyad());
        kullanici.setEmail(updateDTO.getEmail());
        if (updateDTO.getSifre() != null && !updateDTO.getSifre().isEmpty()) {
            kullanici.setSifre(updateDTO.getSifre());
        }
        kullanici.setTc(updateDTO.getTc());
        kullanici.setDogumYili(updateDTO.getDogumYili());

        return kullanicilarRepository.save(kullanici);
    }

    public void deleteUser(int id){
        if (!kullanicilarRepository.existsById(id)){
            throw new IllegalArgumentException("Kullanıcı bulunamadı: "+id);
        }
        kullanicilarRepository.deleteById(id);
    }

//
//    public UserDetails loadUserById(int userId) {
//        Kullanicilar user = kullanicilarRepository.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + userId));
//
//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRol()));
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getSifre(), authorities);
//    }


    public UserDetails loadUserById(Integer id) {
        Kullanicilar user = kullanicilarRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));

    //Kullanicilar user = getUserById(id);
    String role = "ROLE_" + user.getRol().toLowerCase();
    log.info("Loading user by ID: {}. Database role: {}, Mapped role: {}",id, user.getRol(), role);
    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
    return User.builder()
                .username(user.getTc())
                .password(user.getSifre())
                .authorities(authorities)
                .build();
}

    public Kullanicilar authenticateUser(String tc, String sifre) {
        Kullanicilar user = kullanicilarRepository.findByTc(tc)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));

        if (!sifre.equals(user.getSifre())) {
            throw new BadCredentialsException("Hatalı şifre");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kullanicilar user = kullanicilarRepository.findByTc(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Create authorities with ROLE_ prefix
        String role = "ROLE_" + user.getRol().toLowerCase();
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(role)
        );

        log.info("Loading user by username: {}. Database role: {}, Mapped role: {}",
                username, user.getRol(), role);

        return User.builder()
                .username(user.getTc())
                .password(user.getSifre())
                .authorities(authorities)
                .build();
    }

    public Kullanicilar getUserById(Integer id) {
        return kullanicilarRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
    }

    public Kullanicilar findByTc(String tc) {
        return kullanicilarRepository.findByTc(tc)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with TC: " + tc));
    }

}