package br.com.fiap.imcapp

import android.graphics.Color.*
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.imcapp.ui.theme.IMCAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                        IMCscreen()
                }
            }
        }
    }
}

@Composable
fun IMCscreen() {

        var peso = remember {
            mutableStateOf("")
        }

        var altura = remember {
            mutableStateOf("")
        }

        var imc = remember {
            mutableStateOf(0.0)
        }

        var statusImc = remember {
            mutableStateOf("")
        }

        var statusColor = remember {
            mutableStateOf(Color(0xFF5C5C5C))
        }


    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                //.height(160.dp)

        ){
            // Header
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.vermelho_fiap))
                    //.height(190.dp)
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.bmi_grande),
                        contentDescription = "Imagem de calculadora",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                    )
                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 40.dp)
                )

            }
            //Formulario
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                    Card (
                        modifier = Modifier
                            .offset(y = (-25).dp)
                            .fillMaxWidth(),
                        colors = CardDefaults
                            .cardColors(containerColor = Color(0xfff9f6f6)),
                        elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                        Column(
                            modifier = Modifier.padding(
                                vertical = 16.dp,
                                horizontal = 32.dp
                            )
                        ) {
                            Text(
                                text = "Seus Dados",
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.vermelho_fiap),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                text = "Seu peso",
                                modifier = Modifier.padding(bottom = 8.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = colorResource(id = R.color.vermelho_fiap),

                            )
                            OutlinedTextField(
                                value = peso.value,
                                onValueChange = {peso.value = it},
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Seu peso em Kg.")
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                    focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                                ),
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Sua Altura",
                                modifier = Modifier.padding(bottom = 8.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = colorResource(id = R.color.vermelho_fiap),

                                )
                            OutlinedTextField(
                                value = altura.value,
                                onValueChange = {altura.value = it},
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Sua altura em CM..")
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                    focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                                ),
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number)

                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                          imc.value = calcularIMC(
                                              altura = altura.value.toDouble(),
                                              peso = peso.value.toDouble()
                                          )
                                    var (statusImcf, color) = determinarClassificacaoIMC(imc.value)
                                    statusImc.value = statusImcf
                                    statusColor.value = Color(parseColor("#$color"))
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                                    id = R.color.vermelho_fiap))
                            ) {
                                Text(
                                    text = "CALCULAR",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                    peso.value = ""
                                    altura.value = ""
                                    imc.value = 0.0
                                    statusImc.value = ""
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                                    id = R.color.vermelho_fiap))
                            ) {
                                Text(
                                    text = "LIMPAR FORMULÁRIO",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }

                    }

            }
            // card resultado
            // --- card resultado
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                colors = CardDefaults.cardColors(containerColor = statusColor.value),
                elevation = CardDefaults.cardElevation(4.dp)
                //border = BorderStroke(width = 1.dp, Color(0xffed145b))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .fillMaxSize()
                ) {
                    Column() {
                        Text(
                            text = "Resultado",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(
                            text = statusImc.value,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                    Text(
                        text = String.format("%.1f", imc.value),
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 36.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}