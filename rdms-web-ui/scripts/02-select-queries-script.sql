/* 1 select user's experiments */
SELECT "ExperimentId", "Name" 
FROM experiments 
WHERE "ExperimentId" = ANY (SELECT "ExperimentId" 
				FROM user_experiments 
				WHERE "UserId" = 122);

/* 1 LINQ:	var userExperimentsNames = _context.Experiments.
                Where(e => _context.UserExperiments.Any(ue => ue.UserId == GetCurrUserId() && ue.ExperimentId == e.ExperimentId)).
                Select(e => new ExperimentNameViewModel { ExperimentId = e.ExperimentId, Name = e.Name }).ToList();*/

/* 2 select user's experiment tests */
SELECT "TestId", "Name" 
FROM tests 
WHERE "ExperimentId" = 244 
AND 
ANY (SELECT "ExperimentId"
	FROM user_experiments 
	WHERE "UserId" = 122 
	AND
	"ExperimentId" = 244);

/* 2 LINQ:	var userExperimentTestsNames = _context.Tests.
                Where(t => t.ExperimentId == id
                && _context.UserExperiments.Any(ue => ue.UserId == GetCurrUserId() && ue.ExperimentId == t.ExperimentId)).
                Select(t => new TestNameViewModel { TestId = t.ExperimentId, Name = t.Name }).ToList();*/