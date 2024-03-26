package info.hridoydas.lifecanvas.auth.domain

import info.hridoydas.lifecanvas.auth.data.UserApiModel
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserApiModel, User> {
    override fun map(from: UserApiModel): User {
       return User(
                avatar = from.avatar,
                email = from.email,
                createAt = from.createAt,
                fullName = from.fullName,
                id = from.id
            )
    }
}
