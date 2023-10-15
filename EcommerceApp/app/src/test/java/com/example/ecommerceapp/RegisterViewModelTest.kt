import com.example.ecommerceapp.RegisterViewModel
import com.example.ecommerceapp.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RegisterViewModelTest {

    private lateinit var registerViewModel: RegisterViewModel

    @Before
    fun setUp() {
        registerViewModel = RegisterViewModel()
    }

    @Test
    fun testIsUserDataValid_ValidUserData_ReturnsTrue() {
        val user = User("JohnDoe", "Pass1234", "john@example.com", "123 Main St")
        val result = registerViewModel.isUserDataValid(user)
        Assert.assertEquals(true,result)
    }

    @Test
    fun testIsUserDataValid_InvalidUserData_ReturnsFalse() {
        val user = User("JD", "Pass", "invalid-email", "St")
        val result = registerViewModel.isUserDataValid(user)
        Assert.assertEquals(false ,result)
    }

    @Test
    fun testIsEmailValid_ValidEmail_ReturnsTrue() {
        val email = "john@example.com"
        val result = registerViewModel.isEmailValid(email)
        Assert.assertEquals(true,result)
    }

    @Test
    fun testIsEmailValid_InvalidEmail_ReturnsFalse() {
        val email = "invalid-email"
        val result = registerViewModel.isEmailValid(email)
        Assert.assertEquals(false ,result)
    }

    @Test
    fun testIsUsernameValid_ValidUsername_ReturnsTrue() {
        val username = "JohnDoe"
        val result = registerViewModel.isUsernameValid(username)
        Assert.assertEquals(true,result)
    }

    @Test
    fun testIsUsernameValid_InvalidUsername_ReturnsFalse() {
        val username = "JD"
        val result = registerViewModel.isUsernameValid(username)
        Assert.assertEquals(false,result)
    }

    @Test
    fun testIsPasswordValid_ValidPassword_ReturnsTrue() {
        val password = "Pass1234"
        val result = registerViewModel.isPasswordValid(password)
        Assert.assertEquals(true,result)
    }

    @Test
    fun testIsPasswordValid_InvalidPassword_ReturnsFalse() {
        val password = "123"
        val result = registerViewModel.isPasswordValid(password)
        Assert.assertEquals(false,result)
    }

    @Test
    fun testIsAddressValid_ValidAddress_ReturnsTrue() {
        val address = "100 Main St"
        val result = registerViewModel.isAddressValid(address)
        Assert.assertEquals(true,result)
    }

    @Test
    fun testIsAddressValid_InvalidAddress_ReturnsFalse() {
        val address = "St"
        val result = registerViewModel.isAddressValid(address)
        Assert.assertEquals(false,result)
    }
}
