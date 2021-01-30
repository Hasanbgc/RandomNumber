package com.hasan.rnumberfromfservice.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.hasan.rnumberfromfservice.R
import com.hasan.rnumberfromfservice.viewmodels.MainViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [NumberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumberFragment : Fragment(){
    //region Declaration
    private var param1: String? = null
    private var param2: String? = null
    lateinit var numberTextView:TextView
    //endregion


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // region init
        val view = inflater.inflate(R.layout.fragment_number_and_timer, container, false)
        numberTextView = view.findViewById<TextView>(R.id.tvRandomNumberId)

        //endregion
        val model : MainViewModel by viewModels()
        val vModel: MainViewModel? =
            activity?.let { ViewModelProvider(it) }?.get(MainViewModel::class.java)

        if (vModel != null) {
            vModel.getData().observe(viewLifecycleOwner, Observer {
                numberTextView.setText("Number : $it")
            })
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NumberAndTimerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NumberFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    override fun onDetach() {
        super.onDetach()
    }

}
