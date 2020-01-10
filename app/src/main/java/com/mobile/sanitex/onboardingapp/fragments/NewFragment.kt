package com.mobile.sanitex.onboardingapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mobile.sanitex.onboardingapp.GlideApp
import com.mobile.sanitex.onboardingapp.R
import com.mobile.sanitex.onboardingapp.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_new.*


class NewFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_new, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProviders.of(activity!!).get(NewsViewModel::class.java).apply {
            if (arguments != null){
                news.value?.getOrNull(NewFragmentArgs.fromBundle(arguments!!).position).let { new ->
                    if (new != null){
                        GlideApp.with(requireContext()).load(new.image).into(activity!!.findViewById<ImageView>(
                            R.id.app_bar_image))
                        title.text = new.title
                        text.text = new.text
                        author.text = new.author
                        date.text = new.getFormattedDate()

                        readFull.setOnClickListener {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(new.url))
                            startActivity(browserIntent)
                        }

                    } else
                        activity?.onBackPressed()
                }
            } else
                activity?.onBackPressed()
        }
    }

}