package br.com.zup.zoologico.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.zoologico.CAMPO_OBRIGATORIO
import br.com.zup.zoologico.CHAVE_ANIMAL
import br.com.zup.zoologico.R
import br.com.zup.zoologico.adapter.AnimalAdapter
import br.com.zup.zoologico.databinding.FragmentCadastroAnimaisBinding
import br.com.zup.zoologico.model.Animal

class CadastroAnimaisFragment : Fragment() {

    private lateinit var binding: FragmentCadastroAnimaisBinding
    private val adapter: AnimalAdapter by lazy {
        AnimalAdapter(arrayListOf(), this::irParaDetalhe)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastroAnimaisBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exibirRecyclerView()

        binding.btnAdicionar.setOnClickListener {
            adicionarAnimalNaLista()
        }
    }

    private fun irParaDetalhe(animal: Animal){
        val bundle = bundleOf(CHAVE_ANIMAL to animal)

        NavHostFragment.findNavController(this).navigate(R.id.action_cadastroAnimaisFragment_to_detalhesAnimaisFragment,bundle)

    }

    private fun exibirRecyclerView(){
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(context)

    }

    private fun adicionarAnimalNaLista(){
        val listaDeAnimais = mutableListOf<Animal>()
        val animalDigitado = recuperarAnimalDigitado()

        if(animalDigitado != null){
            listaDeAnimais.add(animalDigitado)
            adapter.atualizarLista(listaDeAnimais)
            exibirRecyclerView()
        }
        else{
            exibirErro()
        }

    }

    private fun recuperarAnimalDigitado(): Animal?{
        val nomeAnimal = binding.edAnimal.text.toString()
        val descricaoAnimal = binding.edDescricao.text.toString()

        if (nomeAnimal.isNotEmpty() && descricaoAnimal.isNotEmpty()){
            limparCampos()
            return Animal(nomeAnimal, descricaoAnimal)
        }
        return null
    }

    private fun exibirErro(){
        binding.edAnimal.error = CAMPO_OBRIGATORIO
        binding.edDescricao.error = CAMPO_OBRIGATORIO
    }

    private fun limparCampos(){
        binding.edAnimal.text.clear()
        binding.edDescricao.text.clear()

    }
}