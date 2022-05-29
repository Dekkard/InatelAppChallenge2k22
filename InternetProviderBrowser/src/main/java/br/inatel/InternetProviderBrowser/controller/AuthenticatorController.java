package br.inatel.InternetProviderBrowser.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.InternetProviderBrowser.config.security.TokenService;
import br.inatel.InternetProviderBrowser.config.security.UserService;
import br.inatel.InternetProviderBrowser.config.security.model.LoginForm;
import br.inatel.InternetProviderBrowser.config.security.model.Perfis;
import br.inatel.InternetProviderBrowser.config.security.model.TokenDTO;
import br.inatel.InternetProviderBrowser.config.security.model.User;
import br.inatel.InternetProviderBrowser.config.security.model.UserClientDTO;
import br.inatel.InternetProviderBrowser.config.security.model.UserInstallerDTO;
import br.inatel.InternetProviderBrowser.service.ClientService;
import br.inatel.InternetProviderBrowser.service.InstallerService;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

	@Autowired
	private AuthenticationManager am;

	@Autowired
	private ClientService cs;

	@Autowired
	private InstallerService is;

	@Autowired
	private UserService us;

	@Autowired
	private TokenService ts;

	@PostMapping("/client")
	public ResponseEntity<TokenDTO> postAuthClient(@RequestBody @Valid LoginForm lf) {
		try {
			Authentication a = am.authenticate(lf.converter());
			String token = ts.gerarToken(a, "Client");
			TokenDTO tokenDTO = new TokenDTO(token, "Bearer");
			return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/client/registry")
	public ResponseEntity<HttpStatus> createClient(@RequestBody @Valid UserClientDTO userbody) {
		try {
			cs.insert(userbody.getClientInfo());
			User user = userbody.getUserInfo();
			List<Perfis> lp = new ArrayList<>();
			lp.add(us.findPerfil(1l));
			user.setPerfis(lp);
			us.insert(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/installer")
	public ResponseEntity<TokenDTO> postAuthInstaller(@RequestBody @Valid LoginForm lf) {
		try {
			Authentication a = am.authenticate(lf.converter());
			String token = ts.gerarToken(a, "Installer");
			TokenDTO tokenDTO = new TokenDTO(token, "Bearer");
			return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/installer/registry")
	public ResponseEntity<HttpStatus> createInstaller(@RequestBody @Valid UserInstallerDTO userbody) {
		try {
			is.insert(userbody.getInstallerInfo());
			User user = userbody.getUserInfo();
			List<Perfis> lp = new ArrayList<>();
			lp.add(us.findPerfil(2l));
			user.setPerfis(lp);
			us.insert(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
