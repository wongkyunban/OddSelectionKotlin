package com.wong.odd

data class DataBean(var name: String) {
    @Transient
    var isChecked = false

}