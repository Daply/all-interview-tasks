using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace Administrators_Service_Station.Models
{
    public class DbInit : DropCreateDatabaseAlways<AdminContext>
    {
        protected override void Seed(AdminContext db)
        {
            ICollection<Order> orders = new List<Order>();
            Order order1 = new Order { OrderId = 1, Order_Status = Order.Status.Completed, Date = DateTime.Now, Order_Amount = 10000, CarId = 1 };
            Order order2 = new Order { OrderId = 2, Order_Status = Order.Status.In_Progress, Date = DateTime.Now, Order_Amount = 5000, CarId = 1 };
            Order order3 = new Order { OrderId = 3, Order_Status = Order.Status.Cancelled, Date = DateTime.Now, Order_Amount = 100 };

            orders.Add(order1);
            orders.Add(order2);

            ICollection<Car> cars1 = new HashSet<Car>();
            ICollection<Car> cars2 = new HashSet<Car>();
            Car car1 = new Car { CarId = 1, Make = "in process", Model = "Subaru", Year = 2016, VIN = "5566RTS", Orders = orders, ClientId = 1};
            Car car2 = new Car { CarId = 2, Make = "done", Model = "Mazerati", Year = 2016, VIN = "8473UTY", ClientId = 1};
            Car car3 = new Car { CarId = 3, Make = "in process", Model = "Toyota", Year = 2015, VIN = "0943OIH", ClientId = 2 };

            db.Cars.Add(car1);
            db.Cars.Add(car2);
            db.Cars.Add(car3);

            cars1.Add(db.Cars.Find(1));
            cars1.Add(db.Cars.Find(2));
            cars2.Add(db.Cars.Find(3));

            db.Clients.Add(new Client { ClientId = 1, First_Name = "Stewart", Last_Name = "Jackson", Date_Of_Birth = new DateTime(1990, 5, 28), Address = "43 Street", Phone = "1234567", Email = "stu@gmail.com", Cars = cars1 });
            db.Clients.Add(new Client { ClientId = 2, First_Name = "Ashley", Last_Name = "Nill", Date_Of_Birth = new DateTime(1993, 3, 24), Address = "78 Street", Phone = "8910111", Email = "ash@gmail.com", Cars = cars2 });
            db.Clients.Add(new Client { ClientId = 3, First_Name = "David", Last_Name = "Curson", Date_Of_Birth = new DateTime(1989, 7, 16), Address = "11 Street", Phone = "2131415", Email = "dave@gmail.com" });

            base.Seed(db);
        }
    }
}