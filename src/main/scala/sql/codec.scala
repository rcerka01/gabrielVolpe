package sql

import skunk.Codec
import skunk.codec.all.{uuid, varchar}
import shop.domain.Brands._

object codec {
  val brandId: Codec[BrandId]     = uuid.imap[BrandId](BrandId(_))(_.value)
  val brandName: Codec[BrandName] = varchar.imap[BrandName](BrandName(_))(_.value)
}
