package com.example.sprinklerassignement

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.sprinklerassignement.data.FILTER_TYPE
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_filter_selection.*


class FilterFragment: BottomSheetDialogFragment() {

    companion object {
        private const val KEY_SELECTED_FILTER = "KEY_SELECTED_FILTER"

        private fun newInstance(filterType: String): FilterFragment {
            return FilterFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_SELECTED_FILTER, filterType)
                }
            }
        }

        fun show(fragmentManager: FragmentManager, filterType: String) {
            val instance = newInstance(filterType)
            instance.setStyle(DialogFragment.STYLE_NORMAL, R.style.BaseBottomSheetDialog)
            instance.show(fragmentManager, instance.tag)
        }
    }

    private var mPreviousView:View? = null


    private var mClickListener: FilterClickListener?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_filter_selection, container, false)
    }

    override fun onAttach(context: Context) {
        if (context is FilterClickListener && context is Activity) {
            mClickListener = activity as FilterClickListener
        }
        super.onAttach(context)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
            BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!).state =
                BottomSheetBehavior.STATE_EXPANDED
        }

        // Do something with your dialog like setContentView() or whatever
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setFilterState()
    }

    private fun toggleSelectedState(view: View, isSelected: Boolean) {
        if (isSelected) {
            mPreviousView = view
        }
        context?.let {context->
            if (isSelected) {
                view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_light_blue_rectangle_rounded))
            } else {
                view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_grey_rectangle_rounded))
            }
        }

    }

    private fun setFilterState() {
        val previousFilter: String = arguments?.getString(KEY_SELECTED_FILTER)?:FILTER_TYPE.NONE.name
        when(previousFilter) {
            FILTER_TYPE.TRENDING.name -> {
                mPreviousView = tv_trending
                toggleSelectedState(tv_trending, true)
            }
            FILTER_TYPE.MACHINE_LEARNING.name -> {
                mPreviousView = tv_machine
                toggleSelectedState(tv_machine, true)
            }
            FILTER_TYPE.BOOKMARK.name -> {
                mPreviousView = tv_bookmark
                toggleSelectedState(tv_bookmark, true)
            }
            FILTER_TYPE.ED_TECH.name -> {
                mPreviousView = tv_edtech
                toggleSelectedState(tv_edtech, true)
            }
            FILTER_TYPE.MEDICAL_TECH.name -> {
                mPreviousView = tv_medical
                toggleSelectedState(tv_medical, true)
            }
            FILTER_TYPE.NONE.name -> {
                mPreviousView = tv_none
                toggleSelectedState(tv_none, true)
            }
        }
    }

    private fun clearPreviousSelection() {
        mPreviousView?.let {
            toggleSelectedState(it, false)
        }
    }

    private fun setListener() {
        tv_bookmark.setOnClickListener {
            clearPreviousSelection()
            toggleSelectedState(tv_bookmark, true)
        }

        tv_machine.setOnClickListener {
            clearPreviousSelection()
            toggleSelectedState(tv_machine, true)
        }

        tv_edtech.setOnClickListener {
            clearPreviousSelection()
            toggleSelectedState(tv_edtech, true)
        }

        tv_trending.setOnClickListener {
            clearPreviousSelection()
            toggleSelectedState(tv_trending, true)
        }

        tv_medical.setOnClickListener {
            clearPreviousSelection()
            toggleSelectedState(tv_medical, true)
        }

        tv_none.setOnClickListener {
            clearPreviousSelection()
            toggleSelectedState(tv_none, true)
        }

        tv_continue.setOnClickListener {
            mClickListener?.updateFilterSelection(getCurrentFilter())
            dismissAllowingStateLoss()
        }

    }

    private fun getCurrentFilter():FILTER_TYPE {
        mPreviousView?.let {
           return when(it) {
               tv_bookmark-> FILTER_TYPE.BOOKMARK
               tv_medical -> FILTER_TYPE.MEDICAL_TECH
               tv_edtech ->FILTER_TYPE.ED_TECH
               tv_trending -> FILTER_TYPE.TRENDING
               tv_none -> FILTER_TYPE.NONE
               tv_machine -> FILTER_TYPE.MACHINE_LEARNING
               else ->{
                   FILTER_TYPE.NONE
               }
            }
        }
        return FILTER_TYPE.NONE
    }

    override fun onDestroy() {
        mPreviousView = null
        super.onDestroy()
    }
}