﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models.Entities
{
    public class DepartmentProject
    {
        public int DepartmentId { get; set; }
        public int ProjectId { get; set; }

        public Department Department { get; set; }
        public Project Project { get; set; }
    }
}
