package com.efeemirteke.canlidovizkurlari

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapterview.view.*

class myRecyclerView(val context: Context,val kurArrayList:ArrayList<KurClass>):RecyclerView.Adapter<myRecyclerView.myViewHolder>(){
    override fun getItemCount(): Int {
        return kurArrayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater=LayoutInflater.from(context)
        val view=inflater.inflate(R.layout.adapterview,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.kuruYerlestir(position,kurArrayList[position])
    }


    inner class myViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val kurIsmi:TextView
        val kursIsmiKısaltma:TextView
        val degisim:TextView
        val satis:TextView
        val alis:TextView
        val img:ImageView
        val degisimIcon:ImageView
        init {
            this.kurIsmi=itemView.tvKurIsim
            this.kursIsmiKısaltma=itemView.tvKurIsimKisaltma
            this.degisim=itemView.tvDegisim
            this.satis=itemView.tvSatis
            this.alis=itemView.tvAlis
            this.img=itemView.imgKur
            this.degisimIcon=itemView.imgDegisim
        }
        fun kuruYerlestir(kurPosition:Int,oankiKur:KurClass){
            kurIsmi.text=oankiKur.paraBirimi
            kursIsmiKısaltma.text="${oankiKur.paraBirimiKisaltma}/TL"
            degisim.text=oankiKur.degisim.toString()
            satis.text=oankiKur.satis.toString()
            alis.text=oankiKur.alis.toString()
            img.setImageResource(oankiKur.img)
            if(oankiKur.degisim!!.contains("-")){
                degisimIcon.setImageResource(R.drawable.down_icon)
                degisim.background=context.getDrawable(R.color.red)

            }
            else{
                degisimIcon.setImageResource(R.drawable.iconup)
                degisimIcon.background=context.getDrawable(R.color.green)
            }
        }
    }

}