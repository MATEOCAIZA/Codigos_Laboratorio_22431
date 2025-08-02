const jwt = require('jsonwebtoken');

// Middleware que protege rutas usando JWT
function authMiddleware(req, res, next) {
  // Extrae el token del header Authorization
  const token = req.headers.authorization?.split(" ")[1];
  if (!token) return res.status(401).json({ msg: 'No autorizado' });

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    console.log("Token válido. Payload:", decoded);
    req.userId = decoded.id;
    next();
  } catch (err) {
    console.log(token);
    console.log("Token inválido:", err.message);
    return res.status(403).json({ msg: 'Token inválido' });
  }
  
}

module.exports = authMiddleware;