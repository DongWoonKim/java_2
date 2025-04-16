variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "ap-northeast-2"
}

variable "vpc_name" {
  description = "Name of VPC"
  type        = string
  default     = "eks-vpc"
}

variable "availability_zones" {
  description = "Availability zones"
  type        = list(string)
  default     = ["ap-northeast-2a", "ap-northeast-2c"]
}

variable "vpc_cidr" {
  description = "CIDR block for VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "public_subnet_cidrs" {
  description = "Public subnet CIDR values"
  type        = list(string)
  default     = ["10.0.101.0/24", "10.0.102.0/24"]
}

variable "private_subnet_cidrs" {
  description = "Private subnet CIDR values"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24"]
}

variable "cluster_name" {
  description = "Name of the EKS cluster"
  type        = string
  default     = "polar"
}

variable "environment" {
  description = "Environment name"
  type        = string
  default     = "dev"
}

variable "kubernetes_version" {
  description = "Kubernetes version"
  type        = string
  default     = "1.32"
}
 
 variable "node_group_desired_size" {
  description = "Desired number of worker nodes"
  type        = number
  default     = 1
}

variable "node_group_max_size" {
  description = "Maximum number of worker nodes"
  type        = number
  default     = 4
}

variable "node_group_min_size" {
  description = "Minimum number of worker nodes"
  type        = number
  default     = 1
}

variable "node_instance_types" {
  description = "List of instance types for the worker nodes"
  type        = list(string)
  default     = ["t3.medium"]
}

/*
CIDR(Classless Inter-Domain Routing) 주소 체계
1. VPC CIDR "10.0.0.0/16":
- /16은 첫 16비트가 네트워크 부분이라는 의미
- 즉, 10.0.까지가 고정
- 나머지 0.0 부분을 사용 가능 (약 65,536개의 IP 주소)
```
10.0.0.0 ~ 10.0.255.255 사용 가능
```

2. 서브넷 CIDR "/24":
- /24는 첫 24비트가 네트워크 부분
- 즉, 마지막 8비트만 호스트 부분 (256개의 IP)
*/