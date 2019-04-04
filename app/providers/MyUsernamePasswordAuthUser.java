package providers;

import providers.MyUsernamePasswordAuthProvider.MySignup;

import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
import com.feth.play.module.pa.user.NameIdentity;
import com.feth.play.module.pa.user.FirstLastNameIdentity;

public class MyUsernamePasswordAuthUser extends UsernamePasswordAuthUser
        implements NameIdentity, FirstLastNameIdentity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String firstname;
    private final String lastname;

    public MyUsernamePasswordAuthUser(final MySignup signup) {
        super(signup.password, signup.email);
        this.name = signup.getName();
        this.firstname = signup.getFirstname();
        this.lastname = signup.getLastname();
    }

    /**
     * Used for password reset only - do not use this to signup a user!
     * @param password
     */
    public MyUsernamePasswordAuthUser(final String password) {
        super(password, null);
        name = null;
        firstname = null;
        lastname = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFirstName() {
        return firstname;
    }

    @Override
    public String getLastName() {
        return lastname;
    }
}