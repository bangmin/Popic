var express = require('express');
var router = express.Router();

var teacher = require('../query/teacher');



/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/test', (req, res, next) => {
  const result = teacher.create('test');
  res.render(result);
})


module.exports = router;
