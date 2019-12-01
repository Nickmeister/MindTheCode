﻿using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Timesheet.Models;

namespace Timesheet.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public DbSet<Project> Projects { get; set; }
        public DbSet<TimesheetEntry> TimesheetEntries { get; set; }
        public DbSet<Department> Departments { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<Role> Roles { get; set; }

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            /** User **/
            modelBuilder.Entity<User>()
                .HasKey(u => u.ID);

            /** Role **/
            modelBuilder.Entity<Role>()
                .HasKey(r => r.ID);

            /** Department **/
            modelBuilder.Entity<Department>()
                .HasKey(d => d.ID);

            /** TimesheetEntry **/
            modelBuilder.Entity<TimesheetEntry>()
                .HasKey(t => t.ID);

            modelBuilder.Entity<TimesheetEntry>()
                .HasOne(t => t.User);

            /** Project **/
            modelBuilder.Entity<Project>()
                .HasKey(p => p.ID);

            /** DepartmentProject **/
            modelBuilder.Entity<DepartmentProject>()
                .HasKey(dp => new { dp.DepartmentID, dp.ProjectID });

            modelBuilder.Entity<DepartmentProject>()
                .HasOne(dp => dp.Department)
                .WithMany(d => d.DepartmentProjects)
                .HasForeignKey(dp => dp.DepartmentID);

            modelBuilder.Entity<DepartmentProject>()
                .HasOne(dp => dp.Project)
                .WithMany(p => p.DepartmentProjects)
                .HasForeignKey(dp => dp.ProjectID);

        }
    }
}
