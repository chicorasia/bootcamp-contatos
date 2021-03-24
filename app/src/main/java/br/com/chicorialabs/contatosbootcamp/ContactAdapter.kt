package br.com.chicorialabs.contatosbootcamp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val contactList: ArrayList<Contact>) : RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contato_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindItem(contactList[position])
    }

    override fun getItemCount() = contactList.size

}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val nomeTxt = itemView.findViewById(R.id.card_nome) as TextView
    val telefoneTxt = itemView.findViewById(R.id.card_telefone) as TextView
    val nomeAlternativoTxt = itemView.findViewById(R.id.card_nome_alternativo) as TextView

    fun bindItem(contact: Contact){
        nomeTxt.text = contact.nome
        telefoneTxt.text = contact.telefone
        nomeAlternativoTxt.text = contact.nomeAlternativo
    }
}
