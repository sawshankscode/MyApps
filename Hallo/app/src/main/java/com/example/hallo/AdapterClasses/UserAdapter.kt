package com.example.hallo.AdapterClasses
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.hallo.ModelClasses.Users
import com.example.hallo.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_search_item_layput.view.*

class UserAdapter(
    mContext:Context,
    mUsers:List<Users>,
    isChatCheck:Boolean
): RecyclerView.Adapter<UserAdapter.ViewHolder?>()
 {
     private val mContext:Context
     private val mUsers:List<Users>
     private var isChatCheck:Boolean
     init{
         this.isChatCheck=isChatCheck
         this.mUsers=mUsers
         this.mContext=mContext
     }
     override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layput,viewGroup,false)
         return UserAdapter.ViewHolder(view)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         var user:Users? =mUsers[position]
         holder.userNameTxt.text=user!!.getUsername()
         Picasso.get().load(user.getProfile()).placeholder(R.drawable.profile).into(holder.profileImageView)}

     override fun getItemCount(): Int {
            return mUsers.size
     }
     class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var userNameTxt:TextView
         var profileImageView:CircleImageView
         var onlineImageView:CircleImageView
         var offlineImageView:CircleImageView
         var lastMessageTxt:TextView

         init{
             userNameTxt=itemView.findViewById(R.id.username)
             profileImageView=itemView.findViewById(R.id.profile_image)
             onlineImageView=itemView.findViewById(R.id.image_online)
             offlineImageView=itemView.findViewById(R.id.image_offline)
             lastMessageTxt=itemView.findViewById(R.id.msg_last)
         }
     }


 }