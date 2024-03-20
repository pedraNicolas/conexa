package com.pedra.conexacomponents.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.pedra.conexacomponents.R
import com.pedra.conexacomponents.interfaces.OnTextChangeListener

open class SearchComponent@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var onTextChangeListener: OnTextChangeListener? = null
    private lateinit var etQuery: EditText
    private lateinit var ibClearQuery: ImageButton

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val a = getContext().theme.obtainStyledAttributes(attrs, com.pedra.core.R.styleable.SearchTextBase, 0, 0)
        val hint = a.getString(com.pedra.core.R.styleable.SearchTextBase_hint)
        inflate(getContext(), R.layout.search_layout, getComponentPlaceHolder())
        etQuery = findViewById(R.id.etSearch)
        etQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (onTextChangeListener != null) {
                    onTextChangeListener!!.onTextChanged(s.toString())
                }
                onQueryChanged(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })
        ibClearQuery = findViewById(R.id.ibClearQuery)
        ibClearQuery.setOnClickListener { onClearQueryClick() }
    }

    protected fun getComponentPlaceHolder(): LinearLayout {
        return this
    }

    fun onQueryChanged(query: CharSequence) {
        if (onTextChangeListener != null) {
            onTextChangeListener!!.onTextChanged(query.toString())
        }
    }

    fun onClearQueryClick() {
        etQuery.setText("")
        filter("")
    }

    fun filter(query: String) {}

    fun setOnTextChangedListener(onTextChangedListener: OnTextChangeListener?) {
        this.onTextChangeListener = onTextChangedListener
    }
}