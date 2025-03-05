const express = require('express');
const mongoose = require('mongoose');
const { getLlibres, saveLlibres, getByDate } = require('./src/api/api');
const app = express();
const port = process.env.PORT || 3030;

// Middleware para parsear JSON
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Conectar a MongoDB
mongoose.connect('mongodb+srv://Abdul:abdul1234@cluster1.8tcez.mongodb.net/LlibCat?retryWrites=true&w=majority&appName=Cluster1', {
  useNewUrlParser: true,
  useUnifiedTopology: true
})
  .then(() => console.log('Connected to MongoDB'))
  .catch(err => console.log('Error connecting to MongoDB:', err));

// Definir rutas usando las funciones importadas
app.get('/list', getLlibres);
app.post('/add', saveLlibres);
app.get('/list/:dataini/:datafi', getByDate);

/*
app.get('/checkLogin', async (req, res) => {
  try {
    const { gmail, password } = req.query;
    const validUser = (gmail === 'ab@gmail.com' && password === '1234');
    
    res.status(200).send(validUser.toString());
  } catch (err) {
    console.error('Error in checkLogin:', err);
    res.status(500).send('false');
  }
});
*/
// Iniciar servidor
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
