package com.efeemirteke.canlidovizkurlari

import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapterview.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val butunBilgiler=ArrayList<KurClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        veriYukleme()
    }
    override fun onResume() {
        super.onResume()
    }
    fun veriYukleme(){
        val queue=Volley.newRequestQueue(this)
        val url="https://finans.truncgil.com/today.json"
        val data=JsonObjectRequest(Request.Method.GET,url,null,object:Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                //Çektiğimiz Veri Kaynağı Kurlari Object türünde yazdığı için for döngüsüne sokamıyorum para birimleri tek tek yazmam gerekiyor!!
                val dolar=response?.getJSONObject("USD")
                val euro=response?.getJSONObject("EUR")
                val sterlin=response?.getJSONObject("GBP")
                val isvicre_Franki=response?.getJSONObject("CHF")
                val kanada_dolari=response?.getJSONObject("CAD")
                val ruble=response?.getJSONObject("RUB")
                val bae_dirhem=response?.getJSONObject("AED")
                val japon_yeni=response?.getJSONObject("JPY")
                val kuveyt_Dinari=response?.getJSONObject("KWD")
                val norvec_kronu=response?.getJSONObject("NOK")
                val irak_Dinari=response?.getJSONObject("IQD")
                val guney_afrika_randi=response?.getJSONObject("ZAR")
                val avustralya_Dolari=response?.getJSONObject("AUD")
                val libya_Dinari=response?.getJSONObject("LBP")

                //Array
                val img_array= arrayOf(R.drawable.dolar,R.drawable.ab,R.drawable.ingiltere,R.drawable.isvicre,R.drawable.canada,R.drawable.rusia,R.drawable.bae,R.drawable.japan,R.drawable.kuveyt
                    ,R.drawable.norvec,R.drawable.iraq,R.drawable.guney_afrika,R.drawable.avustralya,R.drawable.libya)
                val alis_Array= arrayListOf<String?>()
                val satis_Array= arrayListOf<String?>()
                val degisim= arrayListOf<String?>()
                val paraBirimi= arrayListOf<String>("Dolar","Euro","Sterlin","İsviçre Frankı","Kanada Doları","Rus Rublesi","BAE Dirhemi",
                    "Japon Yeni","Kuveyt Dinarı","Norveç Kronu","Irak Dinarı","G. Afrika Randı","Avustralya Doları","Libya Dinarı")
                val paraBirimiObject= arrayOf(dolar,euro,sterlin,isvicre_Franki,kanada_dolari,ruble,bae_dirhem,japon_yeni,kuveyt_Dinari,
                    norvec_kronu,irak_Dinari,guney_afrika_randi,avustralya_Dolari,libya_Dinari)
                val paraBirimiKisaltma= arrayOf("USD","EUR","GBP","CHF","CAD","RUB","AED","JPY","KWD","NOK","IQD","ZAR","AUD","LBP")
                for(i in paraBirimiObject){
                    degisim.add(i?.getString("Değişim"))
                    alis_Array.add(i?.getString("Alış"))
                    satis_Array.add(i?.getString("Satış"))
                }
                for(i in 0..img_array.size-1){
                    val xArray=KurClass(paraBirimi[i],paraBirimiKisaltma[i],img_array[i],alis_Array[i],satis_Array[i],degisim[i])
                    butunBilgiler.add(xArray)
                }
                val adapter=myRecyclerView(this@MainActivity,butunBilgiler)
                val layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                recycylerV.adapter=adapter
                recycylerV.layoutManager=layoutManager
            }
        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("Error","Veri Alınamadı!!!")
            }
        })
        queue.add(data)
    }
}