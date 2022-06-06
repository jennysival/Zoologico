package br.com.zup.zoologico.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.zoologico.databinding.AnimalItemBinding
import br.com.zup.zoologico.model.Animal

class AnimalAdapter(
    private var listaDeAnimais: MutableList<Animal>,
    private val clickAnimal: (animal: Animal) -> Unit
): RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    class ViewHolder(val binding: AnimalItemBinding): RecyclerView.ViewHolder(binding.root){
        fun exibirAnimal(animal: Animal){
            binding.textoNomeLista.text = animal.getNome()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = listaDeAnimais[position]
        holder.exibirAnimal(animal)
        holder.binding.cvAnimalLista.setOnClickListener {
            clickAnimal(animal)
        }

    }

    override fun getItemCount(): Int {
        return listaDeAnimais.size
    }

    fun atualizarLista(novaLista: MutableList<Animal>){
        if (listaDeAnimais.size == 0) {
            listaDeAnimais = novaLista
        } else {
            listaDeAnimais.addAll(novaLista)
        }
        notifyDataSetChanged()
    }
}