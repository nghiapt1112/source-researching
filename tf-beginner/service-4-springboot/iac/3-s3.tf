resource "aws_s3_bucket" "example_bucket" {
  bucket = "my-example-bucket"
  acl    = "private"
}

resource "aws_s3_bucket_versioning" "example_bucket_versioning" {
  bucket = aws_s3_bucket.example_bucket.bucket
  status = "Suspended"
}

resource "aws_s3_bucket_lifecycle_configuration" "example_lifecycle" {
  bucket = aws_s3_bucket.example_bucket.bucket

  rule {
    id      = "example-rule"
    status  = "Enabled"

    // Adjust the lifecycle rule as needed.
  }
}
