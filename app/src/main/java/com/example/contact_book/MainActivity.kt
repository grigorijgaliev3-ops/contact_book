package com.example.contact_book

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contact_book.ui.theme.Contact_bookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Contact_bookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ContactScreen()
                }
            }
        }
    }
}

@Composable
fun ContactScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { openDial(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = stringResource(R.string.cd_call),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.btn_call),
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { sendEmail(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = stringResource(R.string.cd_email),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.btn_email),
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { openMap(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = stringResource(R.string.cd_map),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.btn_map),
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { shareContact(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = stringResource(R.string.cd_share),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.btn_share),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContactScreen() {
    Contact_bookTheme {
        Surface {
            ContactScreen()
        }
    }
}
fun openDial(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:+74951234567")
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.error_dial),
            Toast.LENGTH_SHORT
        ).show()
    }
}
fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:contact@example.com")
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.email_subject))
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.error_email),
            Toast.LENGTH_SHORT
        ).show()
    }
}
fun openMap(context: Context) {
    val uri = Uri.parse(context.getString(R.string.map_uri))
    val intent = Intent(Intent.ACTION_VIEW, uri)

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.error_map),
            Toast.LENGTH_SHORT
        ).show()
    }
}
fun shareContact(context: Context) {
    val shareText = context.getString(R.string.share_text)

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
    }

    val chooser = Intent.createChooser(
        intent,
        context.getString(R.string.chooser_title)
    )

    try {
        context.startActivity(chooser)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.error_share),
            Toast.LENGTH_SHORT
        ).show()
    }
}