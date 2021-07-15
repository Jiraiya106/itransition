provider "aws" {
    region  = "eu-central-1"
    access_key = ""
    secret_key = ""
}

module "vpc" {
    source                              = "../../modules/vpc"
    name = "Elisey-VPC"
    environment = "PROD"
    # VPC
    instance_tenancy                    = "dedicated"
    enable_dns_support                  = "true"
    enable_dns_hostnames                = "true"
    assign_generated_ipv6_cidr_block    = "false"
    enable_classiclink                  = "false"
    vpc_cidr                            = "10.0.0.0/16"
    private_subnet_cidrs                = ["10.0.3.0/24"]
    public_subnet_cidrs                 = ["10.0.1.0/24", "10.0.2.0/24"]
    availability_zones                  = ["eu-central-1a", "eu-central-1b"]
    allowed_ports                       = ["80", "443"]

    #Internet-GateWay
    enable_internet_gateway             = "true"
    # EIP
    enable_eip                          = "false"
}
