using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace Administrators_Service_Station.Models
{
    public class AdminContext : DbContext
    {
        public DbSet<Client> Clients { get; set; }
        public DbSet<Car> Cars { get; set; }

        public DbSet<Order> Orders { get; set; }
    }
}