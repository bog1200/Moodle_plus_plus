//package app.romail.moodle_plus_plus.security;
//
//import app.romail.moodle_plus_plus.domain.IdDocument;
//import app.romail.moodle_plus_plus.services.IdDocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.Optional;
//
//@Component
//public class IdDocumentAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//    @Autowired
//    private IdDocumentService idDocumentService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Map credentials = (Map) authentication.getCredentials();
//        String country = (String) credentials.get("country");
//        String pin = (String) credentials.get("pin");
//        Optional<IdDocument> idDocument = idDocumentService.getByLogin(country, pin);
//        if (idDocument.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(idDocument.get().getAccount().getUsername());
//
//
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
//
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
