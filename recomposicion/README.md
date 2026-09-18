# Compra de Entradas - Jetpack Compose

## Integrantes
- Johann Andre Caceres Ruiz
- Daniel Wilston Chura Monroy
- Daniel Bedregal Perez

## Descripción
Aplicación Android desarrollada con **Jetpack Compose** que simula la compra
de entradas para un evento. El usuario puede seleccionar la cantidad de
entradas, añadir extras como canchita o bebida, aplicar un cupón de descuento
y visualizar en tiempo real el resumen de su compra (subtotal, descuento y
total). Al finalizar, un botón permite confirmar la compra mostrando un
mensaje con el monto total.

El proyecto fue construido como práctica de los conceptos de **State** y
**Recomposition** en Compose: cada interacción del usuario modifica un estado
observable y la interfaz se vuelve a dibujar automáticamente, sin necesidad
de actualizar vistas manualmente.

## Tecnologías
- **Kotlin** como lenguaje principal.
- **Jetpack Compose** para construir toda la interfaz de forma declarativa.
- **Material Design 3** (`MaterialTheme`, `Button`, `Checkbox`, `Text`, etc.).
- **State y Recomposition**: `remember`, `mutableStateOf`, `mutableIntStateOf`.
- **Efectos secundarios**: `LocalContext` + `Toast` para confirmar la compra.

## Conceptos de Compose aplicados

| Concepto | Dónde se aplica |
|---|---|
| `@Composable` | Todas las funciones de UI (`CompraEntradasScreen`, `RowCheckbox`). |
| `remember` + `mutableStateOf` | Estado de `cantidad`, `canchita`, `bebida`, `aplicarCupon`. |
| **Recomposition** | Al cambiar cualquier estado, la UI se recalcula sola. |
| **Valores derivados** | `subtotal`, `extras`, `descuento`, `total` (no usan `remember`). |
| **Lambda functions** | `onClick = { ... }`, `onCheckedChange = { ... }`. |
| `Modifier` (orden importa) | `.fillMaxSize().padding(24.dp).verticalScroll(...)`. |
| **Composable reutilizable** | `RowCheckbox` se usa 3 veces para etiquetas distintas. |
| `LocalContext` | Para acceder al contexto y mostrar el `Toast`. |

