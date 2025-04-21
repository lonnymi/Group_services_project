# Projet de Service de Gestion de Groupes

Une application de gestion de tâches basée sur les microservices, construite avec Spring Boot, MongoDB, Docker et Kubernetes.

## Fonctionnalités

- Création et gestion de groupes
- Ajout de tâches aux groupes
- Marquage des tâches comme terminées
- Suppression des tâches
- API RESTful avec Spring Boot
- Base de données MongoDB
- Conteneurisation avec Docker
- Déploiement sur Kubernetes
- CI/CD avec GitHub Actions

## Prérequis

- Java 17
- Maven
- Docker
- Kubernetes (Minikube pour le développement local)
- MongoDB

## Démarrage Rapide

1. **Cloner le dépôt**
```bash
git clone https://github.com/lonnymi/Group_services_project.git
cd todogroup-project
```

2. **Compiler l'application**
```bash
mvn clean package
```

3. **Lancer avec Docker**
```bash
docker build -t group-service .
docker run -p 8081:8081 group-service
```
Si cela ne marche pas il faut faire '''
cd group-service'''

4. **Accéder à l'application**
- Interface Web : http://localhost:8081
- Points d'accès API : http://localhost:8081/api/groups

## Points d'Accès API

### Groupes
- `GET /api/groups` - Liste tous les groupes
- `POST /api/groups` - Crée un nouveau groupe
- `GET /api/groups/{id}` - Récupère un groupe spécifique

### Tâches
- `GET /api/groups/{groupId}/tasks` - Liste les tâches d'un groupe
- `POST /api/groups/{groupId}/tasks` - Ajoute une tâche à un groupe
- `PUT /api/groups/{groupId}/tasks/{taskId}/complete` - Marque une tâche comme terminée
- `DELETE /api/groups/{groupId}/tasks/{taskId}` - Supprime une tâche

## Configuration Docker

Construire l'image :
```bash
docker build -t group-service .
```

Lancer le conteneur :
```bash
docker run -p 8081:8081 group-service
```

## Déploiement Kubernetes

Appliquer les configurations Kubernetes :
```bash
kubectl apply -f k8s/
```

Vérifier le déploiement :
```bash
kubectl get pods
kubectl get services
```

## Structure du Projet

```
todogroup-project/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/groupservice/
│       │       ├── controller/
│       │       ├── model/
│       │       ├── repository/
│       │       └── service/
│       └── resources/
│           └── static/
│               └── index.html
├── k8s/
│   ├── todo-app.yaml
│   ├── mongodb.yaml
│   └── storage.yaml
├── Dockerfile
├── pom.xml
└── README.md
```

## Pipeline CI/CD

Le projet utilise GitHub Actions pour le CI/CD. Le pipeline :
- Compile l'application
- Exécute les tests
- Crée l'image Docker
- Déploie sur Kubernetes

## Développement Local

1. Démarrer MongoDB :
```bash
cd group-service
docker run -d -p 27017:27017 mongo
```

2. Lancer l'application :
```bash
cd group-service
./mvnw spring-boot:run
```

## Contribution

1. Forker le dépôt
2. Créer votre branche de fonctionnalité
3. Commiter vos changements
4. Pousser vers la branche
5. Créer une nouvelle Pull Request

## Licence

Ce projet est sous licence MIT.