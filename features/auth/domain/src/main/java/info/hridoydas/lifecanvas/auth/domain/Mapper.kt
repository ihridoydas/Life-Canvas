package info.hridoydas.lifecanvas.auth.domain

interface Mapper<F, T> {
    fun map(from: F): T
}
