resource "aws_ecs_cluster" "api_cluster" {
  name = "api-cluster"
}

resource "aws_ecs_task_definition" "api_task" {
  family                   = "api-task"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode([
    {
      name      = "api-container"
      image     = "891377373643.dkr.ecr.us-east-1.amazonaws.com/lanchonete-apiproduto:latest"
      portMappings = [
        {
          containerPort = 8080
          protocol      = "tcp"
        }
      ]
      essential = true
    }
  ])

  execution_role_arn = "arn:aws:iam::891377373643:role/LabRole"
}

resource "aws_ecs_service" "api_service" {
  name            = "api-service"
  cluster         = aws_ecs_cluster.api_cluster.id
  task_definition = aws_ecs_task_definition.api_task.arn
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = var.subnet_ids
    security_groups = [aws_security_group.ecs_service_sg.id]
    assign_public_ip = true
  }
}

resource "aws_security_group" "ecs_service_sg" {
  name   = "ecs-service-sg"
  vpc_id = var.vpc_id

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
