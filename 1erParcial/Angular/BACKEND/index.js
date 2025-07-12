const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();
const createAdminUser = require('./utils/CreateAdmin');

const app = express();
const port = process.env.PORT || 5000;

// Middleware
app.use(cors());
app.use(express.json());

// Conectar a MongoDB
mongoose.connect(process.env.MONGODB_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

const connection = mongoose.connection;
connection.once('open', async () => {
  console.log('MongoDB database connection established successfully');

  // Crear usuario administrador si no existe
  //await createAdminUser();
});

// Definir rutas (agrega tus rutas aquí)
const usersRouter = require('./routes/users');
const moviesRouter = require('./routes/movies');
const purchasesRouter = require('./routes/purchases'); // Nueva ruta
const paymentsRouter = require('./routes/payments'); // Nueva ruta

app.use('/api/users', usersRouter);
app.use('/api/movies', moviesRouter);
app.use('/api/purchases', purchasesRouter); // Nueva ruta
app.use('/api/payments', paymentsRouter); // Nueva ruta

app.listen(port, () => {
  console.log(`Server is running on port: ${port}`);
});
