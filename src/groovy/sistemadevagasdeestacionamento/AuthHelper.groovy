package sistemadevagasdeestacionamento

import org.apache.tools.ant.types.resources.comparators.Exists

@Singleton
public final class AuthHelper {
    private String mCurrentUsername

    public String getCurrentUsername() {
        return mCurrentUsername
    }

    public static void signup(String username, String sector, String preferenceType) {
        def userController = new UserController()
        def user = new User(username: username, firstName: "Primeiro nome", lastName: "Último nome", preferredSector: sector, preferenceType: preferenceType)
        userController.save(user)
    }

    public void login(String username) {
        mCurrentUsername = username
    }

    public void logout() {
        mCurrentUsername = null
    }
}