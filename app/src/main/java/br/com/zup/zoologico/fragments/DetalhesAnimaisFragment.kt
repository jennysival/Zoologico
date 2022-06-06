package br.com.zup.zoologico.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.zoologico.CHAVE_ANIMAL
import br.com.zup.zoologico.CHAVE_LISTA
import br.com.zup.zoologico.R
import br.com.zup.zoologico.databinding.FragmentDetalhesAnimaisBinding
import br.com.zup.zoologico.model.Animal

class DetalhesAnimaisFragment : Fragment() {
    private lateinit var binding: FragmentDetalhesAnimaisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalhesAnimaisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recuperarDadosAnimal()

        binding.btnVoltar.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_detalhesAnimaisFragment_to_cadastroAnimaisFragment)
        }
    }

    private fun recuperarDadosAnimal() {
        val animal = arguments?.getParcelable<Animal>(CHAVE_ANIMAL)
        if (animal != null){
            binding.tvNomeAnimal.text = animal.getNome()
            binding.tvDescricao.text = animal.getDescricao()
        }
    }
}