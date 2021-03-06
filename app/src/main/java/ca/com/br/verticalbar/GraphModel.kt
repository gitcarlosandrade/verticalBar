package ca.com.br.verticalbar

data class GraphModel(val value: Double,
                      val estimatedValue: Double,
                      val color: Int,
                      val overColor: Int,
                      val icon: Int = 0,
                      val description: String = "",
                      val listener: (GraphModel) -> Unit = {})