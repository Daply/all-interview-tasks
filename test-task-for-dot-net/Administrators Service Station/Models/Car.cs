using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Administrators_Service_Station.Models
{
    public class Car
    {
        // ID машины
        public int CarId { get; set; }
        // 
        [Required(ErrorMessage = "Make is required")]
        [StringLength(50, MinimumLength = 3, ErrorMessage = "String length must be from 3 to 50 symbols")]
        public string Make { get; set; }
        // модель
        [Required(ErrorMessage = "Model is required")]
        [StringLength(50, MinimumLength = 3, ErrorMessage = "String length must be from 3 to 50 symbols")]
        public string Model { get; set; }
        // год создания
        [Required(ErrorMessage = "Year is required")]
        [Range(1768, 2016, ErrorMessage = "Wrong year(first car was invented in 1768)")]
        public int Year { get; set; }
        // 
        [Required(ErrorMessage = "VIN is required")]
        [StringLength(20, MinimumLength = 4, ErrorMessage = "String length must be from 4 to 20 symbols")]
        public string VIN { get; set; }

        public virtual ICollection<Order> Orders { get; set; }

        public int ClientId { get; set; }
    }
}