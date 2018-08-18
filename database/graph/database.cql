CREATE
  (`1` :Actor {name:"Marlon Brando"}) ,
  (`2` :Actor {name:"Jack Nicholson"}) ,
  (`4` :Movie {title:"The Godfather"}) ,
  (`5` :Movie {title:"The Cry Baby Killer"}) ,
  (`6` :Movie {title:"The Missouri Breaks"}) ,
  (`1`)-[:`ACTS_IN` ]->(`4`),
  (`2`)-[:`ACTS_IN` ]->(`5`),
  (`2`)-[:`ACTS_IN` ]->(`6`),
  (`1`)-[:`ACTS_IN` ]->(`6`)
