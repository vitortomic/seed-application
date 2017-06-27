package actions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import play.db.jpa.JPA;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import services.AuthenticationService;

public class SecureAction extends play.mvc.Action.Simple{

	@Inject
	private AuthenticationService authService;
	
	@SuppressWarnings("deprecation")
	@Override
	public CompletionStage<Result> call(Context ctx) {
		return JPA.withTransaction("default",false, () ->{
			String tokenString = getTokenFromHeader(ctx);
			if(tokenString != null && tokenString.length() > 0 && authService.isTokenValid(tokenString))
				return delegate.call(ctx);
			Result unauthorized = Results.unauthorized("unauthorized");
			return CompletableFuture.completedFuture(unauthorized);
		});
	}
	
	
	private String getTokenFromHeader(Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("Authorization");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) 
        		&& (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}