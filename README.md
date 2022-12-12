# sportradar

For now the project has two variants:
1. It can be used as a library with just using the public methods from GameGacade(can be found in branch main)
2. Can be used as a separate service with own REST API endpoints(branch rest)

#### The project stores all the information  in H2 database.

The application has 4 main methods responsible for the game:
1. start - receives 2 team names
2. update - receives 3 parameters(game id, home team scores, away team scores).
   As it is supposed that multiple games can be in progress state simultaneously the additional Id parameter is needed for the method.
3. finish - Has 1 parameter Id. It serves the same purpose as above.
4. show games inProgress.

### Restrinctions

1. Team name cannot be NULL or empty for any team.
2. Amount of scores cannot be NULL, less than zero or less than the previous value for the same team in the same game.
3. It's impossible to finish the game which is already finished.