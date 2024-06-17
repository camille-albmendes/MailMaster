package br.com.fiap.mailmaster

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.mailmaster.screens.LoginScreen
import br.com.fiap.mailmaster.screens.SignupScreen
import br.com.fiap.mailmaster.ui.theme.MailMasterTheme
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : ComponentActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code:Int=123
    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContent {
            MailMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login" ){
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "cadastro") { SignupScreen() }
                    }
                }
            }
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        if(GoogleSignIn.getLastSignedInAccount(this) != null){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//
//        super.onCreate(savedInstanceState)
//        // Configure Google Sign In inside onCreate method
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.client_id_oauth))
//            .requestEmail()
//            .build()
//        // getting the value of gso inside the GoogleSigninClient
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        setContent {
//            MailMasterTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    LoginScreen(this)
//                }
//            }
//        }
//    }
//
//    // signInGoogle() function
//    public fun signInGoogle(){
//        val signInIntent:Intent = mGoogleSignInClient.signInIntent
//        startActivityForResult(signInIntent, Req_Code)
//    }
//
//    // onActivityResult() function : this is where we provide the task and data for the Google Account
//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==Req_Code){
//            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleResult(task)
//        }
//    }
//
//    // handleResult() function -  this is where we update the UI after Google signin takes place
//    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
//        try {
//            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
//            if (account != null) {
//                UpdateUI(account)
//            }
//        } catch (e:ApiException){
//            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
//        }
//    }
//    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
//    private fun UpdateUI(account: GoogleSignInAccount){
//        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
//            if(task.isSuccessful) {
//                UserDetails.setEmail(this, account.email.toString())
//                UserDetails.setUsername(this, account.displayName.toString())
//                println("CONTA:")
//                println(account.displayName.toString())
//                println(account.email.toString())
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//    }
}