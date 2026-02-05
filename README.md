# Spring Init Test - Déploiement Koyeb

Application Java Spring avec Tomcat intégré, prête à être déployée sur Koyeb avec Docker.

## 🚀 Déploiement sur Koyeb

### Méthode 1: Via l'interface Koyeb

1. Poussez votre code sur GitHub/GitLab
2. Connectez votre dépôt à Koyeb
3. Koyeb détectera automatiquement le `Dockerfile` et `koyeb.yml`
4. Déployez votre service

### Méthode 2: Via Koyeb CLI

```bash
# Installer Koyeb CLI
npm install -g @koyeb/cli

# Se connecter
koyeb login

# Déployer
koyeb service create \
  --name spring-init-test \
  --dockerfile ./Dockerfile \
  --port 8080 \
  --env PORT=8080
```

## 🏗️ Architecture Docker

### Multi-stage build
- **Stage 1**: Maven build avec OpenJDK 8
- **Stage 2**: Runtime légier avec JRE Slim

### Caractéristiques
- Image optimisée (~150MB)
- Health check intégré
- Port configurable via variable d'environnement
- Support des JSP et ressources web

## 📦 Fichiers de déploiement

- `Dockerfile`: Configuration du container Docker
- `.dockerignore`: Optimisation du build Docker
- `docker-compose.yml`: Développement local avec PostgreSQL
- `koyeb.yml`: Configuration spécifique Koyeb

## 🛠️ Développement local

### Avec Docker Compose

```bash
# Démarrer l'application et la base de données
docker-compose up -d

# Voir les logs
docker-compose logs -f app

# Arrêter
docker-compose down
```

### Sans Docker

```bash
# Compiler
mvn clean package

# Exécuter
java -jar target/spring-init-test-1.0.0.jar
```

## 🔧 Configuration

### Variables d'environnement
- `PORT`: Port d'écoute (défaut: 8081)
- `DATABASE_URL`: URL de connexion PostgreSQL
- `DATABASE_USER`: Utilisateur base de données
- `DATABASE_PASSWORD`: Mot de passe base de données

### Base de données
Le projet inclut un script SQL dans `database/base.sql` pour l'initialisation.

## 🌐 Accès à l'application

- **Local**: http://localhost:8081
- **Koyeb**: https://[votre-service].koyeb.app

## 📊 Monitoring

### Health Check
L'application expose un endpoint de santé à la racine `/` vérifié toutes les 30 secondes.

### Logs
Consultez les logs via:
- Koyeb Dashboard
- Koyeb CLI: `koyeb logs [service-name]`

## 🔒 Sécurité

- L'application écoute sur 0.0.0.0
- Les ressources sont servies par Tomcat intégré
- Support du multipart pour les uploads

## 🐛 Dépannage

### Problèmes courants
1. **Port déjà utilisé**: Changez le port avec `PORT=8082`
2. **Base de données inaccessible**: Vérifiez les identifiants
3. **Build échoue**: Nettoyez avec `mvn clean`

### Commandes utiles
```bash
# Vérifier le container Docker
docker ps
docker logs [container-id]

# Tester localement
curl http://localhost:8081
```