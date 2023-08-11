package luana.developerandroid.horadagorjeta

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import luana.developerandroid.horadagorjeta.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCalcular.setOnClickListener {
            calcularGorjeta()
        }

        binding.viewValorDoServicoEditText.setOnKeyListener {
                view, keyCode, _ -> ocultarTecladoComEnter(view, keyCode)
        }
    }

    private fun calcularGorjeta() {
        /*Adicionar .text no final instrui o app a usar o resultado (um objeto EditText) e acessar a
        propriedade text dele*/
        val stringNoCampoDeTexto = binding.viewValorDoServicoEditText.text.toString()
        val valorDoServicoEmDouble = stringNoCampoDeTexto.toDoubleOrNull()

        if(valorDoServicoEmDouble == null) {
            exibirGorjeta(0.0)
            binding.viewValorDoServicoEditText.error = getString(R.string.string_erro_digite_novamente)
            return
        }


        //Calcular porcentagem da gorjeta

        val porcentagemDaGorjeta = when (binding.viewOpcoesDeGorjeta.checkedRadioButtonId) {
            R.id.btn_vinte_porcento -> 0.20
            R.id.btn_dezoito_porcento -> 0.18
            else -> 0.15
        }

        var gorjeta = porcentagemDaGorjeta * valorDoServicoEmDouble


        //Arredondaremos pra cima com a função ceil() da bibli. kotlin.math
        if(binding.switchArredondarGorjeta.isChecked) {
            gorjeta = kotlin.math.ceil(gorjeta)
        }

        //Exibir a gorjeta formatada na tela
        exibirGorjeta(gorjeta)

    }

    private fun exibirGorjeta(gorjeta: Double) {
        //Vamos formatar numeros como moedas dependendo da localidade do app
        //No strings.xml, %s é onde a moeda formatada será inserida
        val gorjetaFormatada = NumberFormat.getCurrencyInstance().format(gorjeta)
        binding.viewGorjetaResultado.text = getString(R.string.string_valor_da_gorjeta, gorjetaFormatada)
    }

    /*
    O handleKeyEvent() é uma função auxiliar particular que oculta o teclado na tela se o parâmetro de entrada keyCode é igual a KeyEvent.KEYCODE_ENTER.
    O InputMethodManager controla se um teclado de software é exibido ou não
    e permite que o usuário escolha qual teclado de software será exibido
    O método retornará "true" se o evento de tecla tiver sido processado. Caso contrário, ele retornará "false".
    */

    private fun ocultarTecladoComEnter(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}