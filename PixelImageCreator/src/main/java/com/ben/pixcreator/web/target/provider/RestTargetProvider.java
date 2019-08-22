
package com.ben.pixcreator.web.target.provider;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.web.RequestFilter;

public class RestTargetProvider {

	// private static final Logger log =
	// LoggerFactory.getLogger(RestTargetProvider.class);
	private static RestTargetProvider instance;

	private URI			baseURI;
	private WebTarget	target;

	public RestTargetProvider() {

		try {
			baseURI = new URI(AppContext.getInstance().propertyContext().get("baseWebTargetURI"));
		} catch (URISyntaxException e) {
			new ExceptionPopUp(e);
		}

		Client client = ClientBuilder.newClient().register(RequestFilter.class);

		GuiFacade gui = GuiFacade.getInstance();
		String email = gui.getLogInfo().getEmail();
		String password = gui.getWebPanel().getLogBean().getData().getPassword();

		if (email.length() > 0 && password.length() > 0) {

			HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic(email, password);

			client.register(auth);

		}

		target = client.target(baseURI);

	}

	public WebTarget getBaseTarget() {

		return target;
	}

}
