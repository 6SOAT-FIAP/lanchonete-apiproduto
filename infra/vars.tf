variable "aws_region" {
  description = "Região da AWS"
  type        = string
  default     = "us-east-1"
}

variable "subnet_ids" {
  description = "Lista de IDs das subnets"
  type        = list(string)
}

variable "vpc_id" {
  description = "VPC ID"
  type        = string
}

variable "ecr_repository" {
  description = "URI do repositório ECR"
  type        = string
}

variable "image_tag" {
  description = "Tag da imagem Docker no ECR"
  type        = string
}