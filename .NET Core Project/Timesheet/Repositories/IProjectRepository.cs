﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Repositories
{
    public interface IProjectRepository : IRepository<Project>
    {
        public List<Project> GetProjectsForDepartment(Department department);
    }
}
