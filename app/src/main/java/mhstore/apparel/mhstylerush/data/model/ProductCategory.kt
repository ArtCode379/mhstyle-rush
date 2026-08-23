package mhstore.apparel.mhstylerush.data.model

import androidx.annotation.StringRes
import mhstore.apparel.mhstylerush.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    WOMEN(R.string.jnvnj_category_women),
    MEN(R.string.jnvnj_category_men),
    ACCESSORIES(R.string.jnvnj_category_accessories),
    FOOTWEAR(R.string.jnvnj_category_footwear),
}
