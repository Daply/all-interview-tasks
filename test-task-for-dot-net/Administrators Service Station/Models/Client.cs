using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Administrators_Service_Station.Models
{
    public class Client
    {
        // ID клиента
        public int ClientId { get; set; }
        // имя
        [Required(ErrorMessage = "First name is required")]
        [StringLength(50, MinimumLength = 3, ErrorMessage = "String length must be from 3 to 50 symbols")]
        public string First_Name { get; set; }
        // фамилия
        [Required(ErrorMessage = "Last name is required")]
        [StringLength(50, MinimumLength = 3, ErrorMessage = "String length must be from 3 to 50 symbols")]
        public string Last_Name { get; set; }
        // дата рождения
        public DateTime Date_Of_Birth { get; set; }
        // адрес
        [Required(ErrorMessage = "Address is required")]
        [StringLength(50, MinimumLength = 3, ErrorMessage = "String length must be from 3 to 50 symbols")]
        public string Address { get; set; }
        // телефон
        [Required(ErrorMessage = "Phone is required")]
        public string Phone { get; set; }
        // email
        [Required(ErrorMessage = "Email is required")]
        [RegularExpression(@"[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}", ErrorMessage = "Incorrect email")]
        public string Email { get; set; }
        //cars
        public virtual ICollection<Car> Cars { get; set; }
    }
}