    package com.example.myapp_01

    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.selection.toggleable
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                MaterialTheme {
                    CompraEntradasScreen()
                }
            }
        }
    }

    // ============================================================
    // COMPOSABLE PRINCIPAL
    // ============================================================
    @Composable
    fun CompraEntradasScreen() {

        // Necesario para mostrar el Toast al comprar
        val context = LocalContext.current

        // ---------- ESTADO (State) ----------
        var cantidad by remember { mutableIntStateOf(2) }
        var canchita by remember { mutableStateOf(false) }
        var bebida by remember { mutableStateOf(false) }
        var aplicarCupon by remember { mutableStateOf(false) }

        //  PRECIOS
        val precioEntrada = 15.0
        val precioCanchita = 10.0
        val precioBebida = 5.0
        val porcentajeDescuento = 0.10 // 10%

        //  CÁLCULOS DERIVADOS
        val subtotal = cantidad * precioEntrada
        val extras = (if (canchita) precioCanchita else 0.0) +
                (if (bebida) precioBebida else 0.0)
        val descuento = if (aplicarCupon) subtotal * porcentajeDescuento else 0.0
        val total = subtotal + extras - descuento

        // UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "COMPRA DE ENTRADAS",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // Cantidad
            Text("Cantidad de entradas", fontWeight = FontWeight.SemiBold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = { if (cantidad > 1) cantidad-- }) {
                    Text("−")
                }
                Text(text = cantidad.toString(), fontSize = 20.sp)
                Button(onClick = { cantidad++ }) {
                    Text("+")
                }
            }

            // Extras
            Text("Extras", fontWeight = FontWeight.SemiBold)

            RowCheckbox(
                label = "Canchita (S/ ${"%.2f".format(precioCanchita)})",
                checked = canchita,
                onCheckedChange = { canchita = it }
            )
            RowCheckbox(
                label = "Bebida (S/ ${"%.2f".format(precioBebida)})",
                checked = bebida,
                onCheckedChange = { bebida = it }
            )

            HorizontalDivider()

            //Cupón
            RowCheckbox(
                label = "Aplicar cupón (10% dscto.)",
                checked = aplicarCupon,
                onCheckedChange = { aplicarCupon = it }
            )

            HorizontalDivider()

            // ----- Resumen -----
            Text("Subtotal: S/ ${"%.2f".format(subtotal)}")
            Text("Descuento: S/ ${"%.2f".format(descuento)}")
            Text(
                text = "Total: S/ ${"%.2f".format(total)}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ----- Botón Comprar -----
            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Compra realizada por S/ ${"%.2f".format(total)}",
                        Toast.LENGTH_LONG
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = cantidad > 0
            ) {
                Text("Comprar")
            }
        }
    }

    // ============================================================
    // COMPOSABLE REUTILIZABLE para cada checkbox con etiqueta
    // ============================================================
    @Composable
    fun RowCheckbox(
        label: String,
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .toggleable(
                    value = checked,
                    onValueChange = onCheckedChange
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checked, onCheckedChange = null)
            Spacer(Modifier.width(8.dp))
            Text(label)
        }
    }