package mhstore.apparel.mhstylerush.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import mhstore.apparel.mhstylerush.data.model.Product
import mhstore.apparel.mhstylerush.data.model.ProductCategory

class ProductRepository {
    private val products =
        listOf(
            Product(
                1,
                "Sienna Linen Blazer",
                "A softly tailored linen blazer with a relaxed fit, natural texture and polished horn-effect buttons.",
                ProductCategory.WOMEN,
                89.0,
                "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?w=1200",
            ),
            Product(
                2,
                "Noir Pleated Dress",
                "A fluid midi dress with sculptural pleats, a defined waist and elegant movement made for evenings out.",
                ProductCategory.WOMEN,
                74.0,
                "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=1200",
            ),
            Product(
                3,
                "Harbour Overshirt",
                "A substantial cotton overshirt designed for easy layering, finished with roomy patch pockets.",
                ProductCategory.MEN,
                68.0,
                "https://images.unsplash.com/photo-1598033129183-c4f50c736f10?w=1200",
            ),
            Product(
                4,
                "Classic Oxford Shirt",
                "Crisp breathable cotton, a clean button-down collar and a timeless regular silhouette.",
                ProductCategory.MEN,
                45.0,
                "https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?w=1200",
            ),
            Product(
                5,
                "Sculpted Leather Bag",
                "A compact structured shoulder bag with an adjustable strap and softly lined interior.",
                ProductCategory.ACCESSORIES,
                59.0,
                "https://images.unsplash.com/photo-1584917865442-de89df76afd3?w=1200",
            ),
            Product(
                6,
                "Amber Cat-Eye Frames",
                "Lightweight statement sunglasses with warm tinted lenses and polished tortoiseshell frames.",
                ProductCategory.ACCESSORIES,
                32.0,
                "https://images.unsplash.com/photo-1511499767150-a48a237f0083?w=1200",
            ),
            Product(
                7,
                "Studio White Trainers",
                "Minimal low-profile trainers with a cushioned footbed and tonal stitching for everyday wear.",
                ProductCategory.FOOTWEAR,
                79.0,
                "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=1200",
            ),
            Product(
                8,
                "Terra Suede Loafers",
                "Supple suede loafers with a flexible sole and refined apron stitching.",
                ProductCategory.FOOTWEAR,
                84.0,
                "https://images.unsplash.com/photo-1614252369475-531eba835eb1?w=1200",
            ),
            Product(
                9,
                "Ivory Knit Cardigan",
                "A cloud-soft cardigan with dropped shoulders and deep rib trims for effortless layering.",
                ProductCategory.WOMEN,
                62.0,
                "https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=1200",
            ),
            Product(
                10,
                "Everyday Wool Coat",
                "A clean single-breasted coat in a warm wool blend, cut to layer comfortably through the season.",
                ProductCategory.MEN,
                129.0,
                "https://images.unsplash.com/photo-1539533018447-63fcce2678e3?w=1200",
            ),
            Product(
                11,
                "Silk Geometry Scarf",
                "A pure silk square with an expressive geometric print and hand-rolled edges.",
                ProductCategory.ACCESSORIES,
                38.0,
                "https://images.unsplash.com/photo-1601924994987-69e26d50dc26?w=1200",
            ),
            Product(
                12,
                "City Chelsea Boots",
                "Streamlined leather boots with elastic side panels and a durable city-ready sole.",
                ProductCategory.FOOTWEAR,
                98.0,
                "https://images.unsplash.com/photo-1608256246200-53e635b5b65f?w=1200",
            ),
        )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
