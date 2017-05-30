package pages

import geb.Page

class SignUpPage extends Page {
    static url = 'signUp/index'

    static at = {
        title ==~ /Sign up/
    }

    //#if($ParkingSpaceBooking)
    def proceed(String username, String preferredSector, String preferential) {
        $("input[name='username']").value(username)
        $("input[name='firstName']").value("Primeiro nome")
        $("input[name='lastName']").value("Último nome")
        $("select[name='preferredSector']").value(preferredSector)
        $("input[name='preferentialType']").value(preferential)
        $("input[name='signUp']").click()
    }
    //#end
}